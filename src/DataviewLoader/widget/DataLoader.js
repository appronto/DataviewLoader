/*global logger*/
/*
    DataviewLoader
    ========================

    @file      : DataviewLoader.js
    @version   : 2.2.1
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

    return declare("DataviewLoader.widget.DataLoader", [_WidgetBase, _TemplatedMixin], {
        templateString: widgetTemplate,

        // DOM elements
        divContent: null,
        divLoader: null,

        // Internal variables. Non-primitives created in the prototype are shared between all widget instances.
        _contextObj: null,

        postCreate: function () {
            logger.debug(this.id + ".postCreate");
            this._updateRendering();
            this._setupEvents();
        },

        update: function (obj, callback) {
            console.log(this.id + ".update on new object");
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
                this._loadAndShowContent();
            }
        },

        uninitialize: function () {
            logger.debug(this.id + ".uninitialize");
        },

        _setupEvents: function () {
            if (this.fadeContent) {
                dojoClass.add(this.divContent, "loaderfade");
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
                        this._loadAndShowContent();
                    }
                }
            } catch(error){
                console.log(this.id + "._updateRendering error occurred: " + JSON.stringify(error));
            }
        },

        _loadAndShowContent: function () {
            logger.debug(this.id + "._loadAndShowContent");
            if (this._contextObj) {
                this._execMf(this.loadingMF, this._contextObj.getGuid(), this._processMicroflowCallback, this._processMicroflowFailure);
            }
        },

        _processMicroflowCallback: function (objs) {
            logger.debug(this.id + "._processMicroflowCallback");
        },

        _processMicroflowFailure: function () {
            if (this.errorText) {
                this.divContent.innerHTML = "<div class=\"text-center\"><h3 class=\"loaderheader\">" + this.errorText + "</h3></div>";
            }
        },

        _resetSubscriptions: function () {
            logger.debug(this.id + "._resetSubscriptions");
            this.unsubscribeAll();

//            if (this._contextObj && (this.refreshAction === "Object" || this.refreshAction === "Attribute")) {
//                console.log(this.id + "._resetSubscriptions setup refresh handler: " + this.refreshAction);
//                this.subscribe({
//                    guid: this._contextObj.getGuid(),
//                    callback: dojoLang.hitch(this, function (guid) {
//                        if (this._loadingStarted == false) {
//                            if (this.pageMF) {
//                                this._pageInitiated = false;
//                            }
//                            if (this.refreshAction == "Attribute" && this.refreshtAttr && this._contextObj.get(this.refreshtAttr)) {
//                                console.log(this.id + ".Refresh triggered on attribute change.");
//                                this._updateRendering();
//                            } else if (this.refreshAction == "Object") {
//                                console.log(this.id + ".Refresh triggered on object change.");
//                                this._updateRendering();
//                            }
//                        } else {
//                            console.log(this.id + ".Refresh skip because of loading started.");
//                        }
//                    })
//                });
//            }
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

require(["DataviewLoader/widget/DataLoader"]);
