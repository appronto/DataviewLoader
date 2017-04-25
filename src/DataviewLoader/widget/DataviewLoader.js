/*global logger*/
/*
    DataviewLoader
    ========================

    @file      : DataviewLoader.js
    @version   : 1.0.0
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
    "dojo/dom-prop",
    "dojo/dom-geometry",
    "dojo/dom-class",
    "dojo/dom-style",
    "dojo/dom-construct",
    "dojo/_base/array",
    "dojo/_base/lang",
    "dojo/text",
    "dojo/html",
    "dojo/_base/event",

    "dojo/text!DataviewLoader/widget/template/DataviewLoader.html"
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoArray, lang, dojoText, dojoHtml, dojoEvent, widgetTemplate) {
    "use strict";

    // Declare widget's prototype.
    return declare("DataviewLoader.widget.DataviewLoader", [ _WidgetBase, _TemplatedMixin ], {
        // _TemplatedMixin will create our dom node using this HTML template.
        templateString: widgetTemplate,

        // Internal variables. Non-primitives created in the prototype are shared between all widget instances.
        _handles: null,
        _contextObj: null,

        // dojo.declare.constructor is called to construct the widget instance. Implement to initialize non-primitive properties.
        constructor: function () {
            logger.debug(this.id + ".constructor");
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
        },

        // mxui.widget._WidgetBase.uninitialize is called when the widget is destroyed. Implement to do special tear-down work.
        uninitialize: function () {
          logger.debug(this.id + ".uninitialize");
            // Clean up listeners, helper objects, etc. There is no need to remove listeners added with this.connect / this.subscribe / this.own.
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

        _execMf: function (mf, guid) {
            console.info(this.id + "._execMf");
            if (mf && guid) {
                mx.ui.action(mf, {
                    async : true,
                    params: {
                        applyto: "selection",
                        guids: [guid]
                    },
                    callback: lang.hitch(this, function (objs) {
                        console.info(this.id + "._execMf start showing content.");
                        this._setPage(this._contextObj, this.divcontent);
                        this._setStyleText(this.divcontent, "display:block;");
                        this._setStyleText(this.divloader, "display:none;");
                        console.info(this.id + "._execMf end showing content.");
                    }),
                    error: function (error) {
                        console.info(error.description);
                    }
                }, this);
            }
        },

        // Rerender the interface.
        _updateRendering: function (callback) {
            console.info(this.id + "._updateRendering");

            if(this._contextObj) {
                this._setStyleText(this.divloader, "display:block;");
                this._setStyleText(this.divcontent, "display:none;");
                this._execMf(this.loadingMF, this._contextObj.getGuid());
            }
            
            // The callback, coming from update, needs to be executed, to let the page know it finished rendering
            this._executeCallback(callback, "_updateRendering");
        },
        
        _setStyleText: function(posElem, content) {
            if (posElem) {
                if (typeof(posElem.style.cssText) != 'undefined') {
                    posElem.style.cssText = content;
                    logger.debug(this.id + '.setStyle update: ' + content);
                } else {
                    posElem.setAttribute('style', content);
                    logger.debug(this.id + '.setStyle set: ' + content);
                }
            }
        },
        
        _setPage: function(pageObj, htmlnode) {
            if (pageObj) {
                console.info(this.id + '._setPage get page with context.');
                var pageContext = new mendix.lib.MxContext();
                pageContext.setTrackObject(pageObj);
                this._ioBind = mx.ui.openForm(this.pageContent, {
                    context: pageContext,
                    location: "content",
                    domNode: htmlnode,
                    callback: lang.hitch(this, function(form) {
                        console.info(this.id + '._loadPage page load. ' + form.id);
                    }),
                    error: function (error) {
                        console.info(error.description);
                    }
                });
            } else {
                console.info(this.id + '._setPage get page.');
                var ioBind = mx.ui.openForm(this.pageContent, {
                    location: "content",
                    domNode: htmlnode,
                    callback: lang.hitch(this, function(form) {
                        console.info(this.id + '._loadPage page load. ' + form.id);
                    }),
                    error: function (error) {
                        console.info(error.description);
                    }
                });
            }
        },

        // Reset subscriptions.
        _resetSubscriptions: function () {
            logger.debug(this.id + "._resetSubscriptions");
            // Release handles on previous object, if any.
            this.unsubscribeAll();
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
