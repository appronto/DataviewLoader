/*global logger*/
/*
    DataviewLoader
    ========================

    @file      : DataviewLoader.js
    @version   : 1.3.1
    @author    : JvdGraaf
    @date      : Mon, 24 Apr 2017 15:02:42 GMT
    @copyright : Appronto
    @license   : Apache2

*/
define([
  "dojo/_base/declare",
  "mxui/widget/_WidgetBase",
  "dijit/_TemplatedMixin",
  "dojo/dom-style",
  "dojo/dom-class",
  "dojo/_base/lang",
  "dojo/_base/event",

  "dojo/text!DataviewLoader/widget/template/DataviewLoader.html"
], function (declare, _WidgetBase, _TemplatedMixin, dojoStyle, dojoClass, dojoLang, dojoEvent, widgetTemplate) {
    "use strict";

    return declare("DataviewLoader.widget.DataviewLoader", [_WidgetBase, _TemplatedMixin], {
        templateString: widgetTemplate,

        // DOM elements
        divContent: null,
        divLoader: null,

        // Internal variables. Non-primitives created in the prototype are shared between all widget instances.
        _handles: null,
        _contextObj: null,
        _loadingStarted: false,
        _pageInitiated: false,
        _form: null,
        active: true,
        prevForm: null,
        refreshHandler: null,

        postCreate: function () {
            logger.debug(this.id + ".postCreate");

            this._updateRendering();
            this._setupEvents();
        },
        update: function (obj, callback) {
            console.log(this.id + ".update on new object");
            this._loadingStarted = false;
            this._pageInitiated = false;
            this._contextObj = obj;
            this._resetSubscriptions();
            this._updateRendering();
            if (callback) {
                callback();
            }
        },

        resize: function (box) {
            console.log(this.id + ".resize");
            // TODO: How to handle tabs and conditional visibility
            if (this.domNode.offsetParent !== null && this.visibilityCheck) {
                if (this.refreshAction == "Attribute" && this.refreshtAttr && this._contextObj) {
                    if (this._contextObj.get(this.refreshtAttr))
                        this._loadAndShowcontent();
                    else
                        console.log(this.id + ".resize Skip because " + this.refreshtAttr + " is false");
                } else {
                    this._loadAndShowcontent();
                }
            }
        },

        uninitialize: function () {
            logger.debug(this.id + ".uninitialize");
            this.active = false;
            if (this.refreshHandler) {
                clearInterval(this.refreshHandler);
                this.refreshHandler = null;
            }
            if (this._form != null) {
                this._form.destroy();
            }
        },

        _setupEvents: function () {
            if (this.fadeContent) {
                dojoClass.add(this.divContent, "loaderfade");
            }

            if (this.refreshTime > 0) {
                this.refreshHandler = setInterval(dojoLang.hitch(this, function () {
                    if (this._loadingStarted == false) {
                        if (this.pageMF) {
                            this._pageInitiated = false;
                        }
                        this._updateRendering();
                    } else {
                        console.log(this.id + ".Refresh skip because of loading started.");
                    }
                }), this.refreshTime * 1000);
            }
        },

        _updateRendering: function () {
            logger.debug(this.id + "._updateRendering");
            
            try{
                if (this._contextObj) {
                    if (this.loadingText && this.divLoader.innerHTML.indexOf(this.loadingText) === -1) {
                        var text = "<div class=\"text-center\"><h3 class=\"loaderheader\">" + this.loadingText + "</h3></div>";
                        this.divLoader.innerHTML = text + this.divLoader.innerHTML;
                    }

                    dojoStyle.set(this.divContent, "display", "none");
                    dojoStyle.set(this.divLoader, "display", "block");

                    if (this.domNode.offsetParent !== null || !this.visibilityCheck) {
                        this._loadAndShowcontent();
                    }
                }
            } catch(error){
                console.log(this.id + "._updateRendering error occurred: " + JSON.stringify(error));
            }
        },

        _loadAndShowcontent: function () {
            logger.debug(this.id + "._loadAndShowcontent");
            if (this._loadingStarted == false) {
                this._loadingStarted = true;
                if (this._contextObj && this.loadingMF) {
                    this._execMf(this.loadingMF, this._contextObj.getGuid(), this._processMicroflowCallback, this._processMicroflowFailure);
                } else if (this._contextObj) {
                    this._setPage(this._contextObj);
                }
            }
        },

        _processMicroflowCallback: function (objs) {
            logger.debug(this.id + '._processMicroflowCallback');
            if (this.active) {
                if (this.asyncCall)
                    this._setPage(this._contextObj);
                else
                    this._setPage(objs[0]);
            } else {
                console.info(this.id + "._processMicroflowCallback Skip loading because widget is destroyed.");
            }
        },
        _processMicroflowFailure: function () {
            if (this.errorText) {
                if (this._pageInitiated)
                    this._form.close();
                this._pageInitiated = false;
                this.divContent.innerHTML = "<div class=\"text-center\"><h3 class=\"loaderheader\">" + this.errorText + "</h3></div>";

                this._showPage();
            }
        },

        _openFormByFormProp: function (pageContext) {
            var props = {
                location: "node",
                domNode: this.divContent,
                callback: dojoLang.hitch(this, this._showPage),
                error: function (error) {
                    console.log(error.description);
                }
            };

            if (pageContext)
                props.context = pageContext;
            this._form = mx.ui.openForm(this.pageContent, props);
        },

        _openFormByMF: function (pageObj, pageContext) {
            this._execMf(this.pageMF, pageObj.getGuid(), function (response) {
                this._form = mx.ui.openForm(response, {
                    context: pageContext,
                    location: "node",
                    domNode: this.divContent,
                    callback: dojoLang.hitch(this, this._showPage),
                    error: function (error) {
                        console.log(error.description);
                    }
                });
            }, function () {
                mx.ui.error("Error calling custom form microflow");
            });
        },

        _setPage: function (pageObj) {
            logger.debug(this.id + '._setPage');

            if (this._pageInitiated) {
                if (this._loadingStarted) {
                    this._showPage();
                } else {
                    console.log(this.id + "_setPage skip because already set.");
                }
            } else {
                this._pageInitiated = true;
                this.divContent.innerHTML = "";
                console.log(this.id + "_setPage");
                if (pageObj) {
                    var pageContext = new mendix.lib.MxContext();
                    pageContext.setTrackObject(pageObj);

                    if (this.pageMF != null && this.pageMF != '')
                        this._openFormByMF(pageObj, pageContext);
                    else
                        this._openFormByFormProp(pageContext);

                } else {
                    if (this.pageMF != null && this.pageMF != '')
                    mx.ui.error("Page microflow is not supported without context");
                    else
                        this._openFormByFormProp();

                }
            }
        },

        _showPage: function (mxform) {
            logger.debug(this.id + "._showPage on form");

            if (this.prevForm != null && mxform != null) {
                this.prevForm.destroy();
            }
            this.prevForm = mxform;

            dojoStyle.set(this.divContent, "display", "block");
            dojoStyle.set(this.divLoader, "display", "none");

            this._loadingStarted = false;
        },

        _resetSubscriptions: function () {
            logger.debug(this.id + "._resetSubscriptions");
            this.unsubscribeAll();

            if (this._contextObj && (this.refreshAction === "Object" || this.refreshAction === "Attribute")) {
                console.log(this.id + "._resetSubscriptions setup refresh handler: " + this.refreshAction);
                this.subscribe({
                    guid: this._contextObj.getGuid(),
                    callback: dojoLang.hitch(this, function (guid) {
                        if (this._loadingStarted == false) {
                            if (this.pageMF) {
                                this._pageInitiated = false;
                            }
                            if (this.refreshAction == "Attribute" && this.refreshtAttr && this._contextObj.get(this.refreshtAttr)) {
                                console.log(this.id + ".Refresh triggered on attribute change.");
                                this._updateRendering();
                            } else if (this.refreshAction == "Object") {
                                console.log(this.id + ".Refresh triggered on object change.");
                                this._updateRendering();
                            }
                        } else {
                            console.log(this.id + ".Refresh skip because of loading started.");
                        }
                    })
                });
            }
        },

        _execMf: function (mf, guid, cb, cbfailure) {
            logger.debug(this.id + "._execMf" + (mf ? ": " + mf : ""));
            if (mf && guid) {
                mx.data.action({
                    params: {
                        applyto: "selection",
                        actionname: mf,
                        guids: [guid]
                    },
                    async: this.asyncCall,
                    origin: this.mxform,
                    callback: (cb && typeof cb === "function" ? dojoLang.hitch(this, cb) : null),
                    error: (cbfailure && typeof cbfailure === "function" ? dojoLang.hitch(this, cbfailure) : null)
                });
            }
        }
    });
});

require(["DataviewLoader/widget/DataviewLoader"]);
