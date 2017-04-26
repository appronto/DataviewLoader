# Dataview Loader widget

When you retrieve large amounts of data, especially with integrations, it takes a while for the pages to load, and the Users can't see or do anything. Mendix shows a loader until ALL data is retrieved, and only then it shows the complete page.

Wouldn't it be better if we could show the User part of the page as it loads, and then the rest once the retrieve over integration is complete?

This widget allows you to show the User parts of the page directly data is retrieved, with individual elements showing a loader. 

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
