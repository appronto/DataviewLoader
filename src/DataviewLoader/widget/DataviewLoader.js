/*global logger*/
/*
    DataviewLoader
    ========================

    @file      : DataviewLoader.js
    @version   : 1.3.0
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
        active: true,

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
            logger.debug(this.id + ".update");

            this._loadingStarted = false;

            this._contextObj = obj;
            this._resetSubscriptions();
            this._updateRendering(callback); // We're passing the callback to updateRendering to be called after DOM-manipulation
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
            logger.debug(this.id + ".resize");

            if (this.domNode.offsetParent !== null) {
                this._loadAndShowcontent();
            }
        },

        // mxui.widget._WidgetBase.uninitialize is called when the widget is destroyed. Implement to do special tear-down work.
        uninitialize: function () {
            console.info(this.id + ".uninitialize");
            // Clean up listeners, helper objects, etc. There is no need to remove listeners added with this.connect / this.subscribe / this.own.
            this.active = false;
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
            logger.debug(this.id + "._setupEvents");
        },

        // Rerender the interface.
        _updateRendering: function (callback) {
            logger.debug(this.id + "._updateRendering");

            if (this._contextObj) {
                dojoStyle.set(this.divContent, "display", "none");
                dojoStyle.set(this.divLoader, "display", "block");
                
                if(this.fadeContent){
                    dojoClass.add(this.divContent, "loaderfade");
                }
            }

            // The callback, coming from update, needs to be executed, to let the page know it finished rendering
            this._executeCallback(callback, "_updateRendering");

            if (this.domNode.offsetParent !== null) {
                this._loadAndShowcontent();
            }
        },

        _loadAndShowcontent: function () {
            logger.debug(this.id + "._loadAndShowcontent");
            if(this._loadingStarted == false){
                this._loadingStarted = true;
                if (this._contextObj && this.loadingMF) {
                    this._execMf(this.loadingMF, this._contextObj.getGuid(), this._processMicroflowCallback);
                } else if (this._contextObj) {
                    this._setPage(this._contextObj, this.divContent);
                }
            }
        },

        _processMicroflowCallback: function (objs) {
            logger.debug(this.id + '._processMicroflowCallback');
            if (this.active) {
                if(this.asyncCall)
                    this._setPage(this._contextObj);
                else
                    this._setPage(objs[0]);
            } else {
                 console.info(this.id + "._processMicroflowCallback Skip loading because widget is destroyed.");
            }
        },

        _setPage: function (pageObj) {
            logger.debug(this.id + '._setPage');
            this.divContent.innerHTML= "";
            
            if (pageObj) {
                var pageContext = new mendix.lib.MxContext();
                pageContext.setTrackObject(pageObj);
                mx.ui.openForm(this.pageContent, {
                    context: pageContext,
                    location: "content",
                    domNode: this.divContent,
                    callback: dojoLang.hitch(this, this._showPage),
                    error: function (error) {
                        console.log(error.description);
                    }
                });
            } else {
                mx.ui.openForm(this.pageContent, {
                    location: "content",
                    domNode: this.divContent,
                    callback: dojoLang.hitch(this, this._showPage),
                    error: function (error) {
                        console.log(error.description);
                    }
                });
            }
        },

        _showPage: function (form) {
            logger.debug(this.id + '._showPage on form ' + form.id);
            
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
            if (this._contextObj && this.refreshAction) {
                logger.debug(this.id + "._resetSubscriptions setup refresh handler");
                this.subscribe({
                    guid: this._contextObj.getGuid(),
                    callback: dojoLang.hitch(this, function (guid) {
                        if(!this._loadingStarted){
                            this._updateRendering();
                        }
                    })
                });
            }
        },

        _execMf: function (mf, guid, cb) {
            logger.debug(this.id + "._execMf" + (mf ? ": " + mf : ""));
            if (mf && guid) {
                mx.ui.action(mf, {
                    async : this.asyncCall,
                    params: {
                        applyto: "selection",
                        guids: [guid]
                    },
                    callback: (cb && typeof cb === "function" ? dojoLang.hitch(this, cb) : null),
                    error: function (error) {
                        console.debug(error.description);
                    }
                }, this);
            }
        },

        _executeCallback: function (cb, from) {
            logger.debug(this.id + "._executeCallback" + (from ? " from " + from : ""));
            if (cb && typeof cb === "function") {
                cb();
            }
        }
    });
});

require(["DataviewLoader/widget/DataviewLoader"]);