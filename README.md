Time-Registration
=================

### Motivation
This is a NOT yet finished application. It has the base functionality done and a working UI, but there is still quite a bit to add. I create this application to get some experience with Spring at a kind of real world example instead of some smaller Hello World tutorials.

### Purpose
The purpose of this application is to track the time one used on a specific project. Since there is usually more than a single person working on said project, it is going to split between:

    * Company
    + Project
    * Coworker
    * Timeframe

### UI
I recently moved the UI to its own project, so that it's more strictly divided what belongs where. Also helps to *compile* the ExtJS files so instead of packacking 10 MB of data it shrinks down to a a few KB. You can find the UI source files at https://github.com/Xezz/Time-Registration-UI


### ToDo
There is quite some stuff to add and fix. Ordered by priority / timeline:

	* Unittest
	* pmd / checkstyle
	* Integrationtest
	* Code Review
	* Spring Security
	* Authentication
	* Authorization