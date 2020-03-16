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

    Documentation
    ========================
    Describe your widget here.
*/

// Required module list. Remove unnecessary modules, you can always get them back from the boilerplate.

define([
  "dojo/_base/declare",
  "mxui/widget/_WidgetBase",
  "dijit/_TemplatedMixin",

  "mxui/dom",
  "dojo/dom",
  "dojo/dom-style",
  "dojo/dom-class",
  "dojo/_base/lang",
  "dojo/_base/event",

  "dojo/text!DataviewLoader/widget/template/DataviewLoader.html"
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoStyle, dojoClass, dojoLang, dojoEvent, widgetTemplate) {
    "use strict";

    // Declare widget's prototype.
    return declare("DataviewLoader.widget.DataviewLoader", [_WidgetBase, _TemplatedMixin], {
        // _TemplatedMixin will create our dom node using this HTML template.
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
        // dojo.declare.constructor is called to construct the widget instance. Implement to initialize non-primitive properties.
        constructor: function () {
            //logger.debug(this.id + ".constructor");
            this._handles = [];
        },

        // dijit._WidgetBase.postCreate is called after constructing the widget. Implement to do extra setup work.
        postCreate: function () {
            logger.debug(this.id + ".postCreate");


            this._updateRendering();
            this._setupEvents();
        },

        // mxui.widget._WidgetBase.update is called when context is changed or initialized. Implement to re-render and / or fetch data.
        update: function (obj, callback) {
            if (this._contextObj !== obj) {
                console.log(this.id + ".update on new object");
                this._loadingStarted = false;
                this._pageInitiated = false;

                this._contextObj = obj;
                this._resetSubscriptions();
                this._updateRendering();
            }
            if (callback) {
                callback();
            }
        },

        // mxui.widget._WidgetBase.enable is called when the widget should enable editing. Implement to enable editing if widget is input widget.
        enable: function () {
            logger.debug(this.id + ".enable");
        },

        // mxui.widget._WidgetBase.enable is called when the widget should disable editing. Implement to disable editing if widget is input widget.
        disable: function () {
            logger.debug(this.id + ".disable");
        },

        // mxui.widget._WidgetBase.resize is called when the page's layout is recalculated. Implement to do sizing calculations. Prefer using CSS instead.
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

        // mxui.widget._WidgetBase.uninitialize is called when the widget is destroyed. Implement to do special tear-down work.
        uninitialize: function () {
            logger.debug(this.id + ".uninitialize");
            // Clean up listeners, helper objects, etc. There is no need to remove listeners added with this.connect / this.subscribe / this.own.
            this.active = false;
            if (this.refreshHandler) {
                clearInterval(this.refreshHandler);
                this.refreshHandler = null;
            }
            if (this._form != null) {
                this._form.destroy();
            }
        },

        // We want to stop events on a mobile device
        _stopBubblingEventOnMobile: function (e) {
            logger.debug(this.id + "._stopBubblingEventOnMobile");
            if (typeof document.ontouchstart !== "undefined") {
                dojoEvent.stop(e);
            }
        },

        // Attach events to HTML dom elements
        _setupEvents: function () {
            // Set fading of content
            if (this.fadeContent) {
                dojoClass.add(this.divContent, "loaderfade");
            }

            // Set refreshing each time
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

        // Rerender the interface.
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
                alert("Error bij aanroepen custom form MF");
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
                        alert("Page microflow is not supported without context");
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

        // Reset subscriptions.
        _resetSubscriptions: function () {
            logger.debug(this.id + "._resetSubscriptions");
            // Release handles on previous object, if any.
            this.unsubscribeAll();

            // When a mendix object exists create subscribtions.
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