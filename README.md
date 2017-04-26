# Dataview Loader widget

When a large data is retrieved, especially with integrations, it takes a while the pages is loaded and the users don't see can do anything. Mendix is showing a loader until all data is retrieved and then showing the complete page.
Wouldn't be better if can show the user parts of the pages and the other parts will be show when the integration is done? 
This widget allows you to directly show the user the page with parts containing a loader until the data is retrieved and the part of the page is shown.   

## Typical usage scenario

You have data that is hard in showing on the page because of integration, hard calculations or it's just a lot of data and you want you users to show page directly but give feedback that the data will come later.

## Features and limitations

This widget executes the microflow containing the integration and is showing a loader image until the microflow is done. Than the content of the part is shown. 
- Let the user show content directly but load content that are slow in retrieving.
- Showing a loader image until the data is done.
- The loader is based on CSS3 animation. The color or appereance can be changed with CSS.

## Installation

- Just download the widget from the appstore!

## Configuration

- Place the widget in a dataview.
- Make a page in a popuplayout containing the dataview of the object where the widget is standing in.
- Make the microflow that will executes the data retrieval.
- Configure the widget with the page and the microflow.
