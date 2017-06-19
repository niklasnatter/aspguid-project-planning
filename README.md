# aspguid-project-planning
The repository contains the source code of an application for the project planning problem that was developed using the _aspguid_ approach.
The application was implemented in the ASP language, annotated with the _aspguid_ language and compiled with the _aspguid_ compiler, which is presented in the in the [aspguid-compiler](https://github.com/nnatter/aspguid-compiler) repository.

An executable version of the application is available as [project-planning.asp.jar](https://nnatter.github.io/aspguid-project-planning/artifact/project-planning.asp.jar).
Example problem instances are accessible as [simple-pizza-project.asp](https://raw.githubusercontent.com/nnatter/aspguid-project-planning/master/asp/simple-pizza-project.asp) and [pizza-project.asp](https://raw.githubusercontent.com/nnatter/aspguid-project-planning/master/asp/pizza-project.asp).
The annotated ASP encoding is available as [project-planning.asp](https://raw.githubusercontent.com/nnatter/aspguid-project-planning/master/asp/project-planning.asp).
A full documentation of the source code is presented in the [javadoc-format](https://nnatter.github.io/aspguid-project-planning/docs/).

## Project Planning Problem
A project consists of one or more tasks, a set of dependencies between tasks and a number of assigned employees. 
Each task of a project has a name, a duration and occupies a defined number of employees. 
For a given project instance, a solution of the project planning problem is a project plan, which consists of a start time and an end time for each task.

## aspguid
_aspguid_ is a new approach for implementing applications usable by users without prior logic programming experience based on annotated ASP programs.
The approach consists of a declarative language for defining graphical user interfaces for ASP programs 
and a compiler that translates ASP programs which are annotated with such definitions into procedural code, that realizes respective graphical user interface assisted applications.

The _aspguid_ techniques significantly reduce the development overhead and minimize the repetitive workload for developers, 
compared to the current state of the art solution of embedding ASP programs into manually developed imperative graphical user interfaces. 

A complete introduction into the _aspguid_ approach is available in the [pdf-format](https://nnatter.github.io/aspguid-compiler/docs/aspguid-thesis.pdf).

## Requirements
* JRE<sup>[1](http://www.oracle.com/technetwork/java/javase/downloads/index.html)</sup> of version 8 or above
* DLV solver<sup>[2](http://www.dlvsystem.com/dlv/)</sup> which is executable through the command `dlv`


## Screenshots
<div align="center">
<img src="https://user-images.githubusercontent.com/13310795/27508801-8d414850-58ed-11e7-947e-5425424bd63e.png" width="200" hspace="5" vspace="29">
<img src="https://user-images.githubusercontent.com/13310795/27508802-8d445d4c-58ed-11e7-81be-e837e53548e0.png" width="200" hspace="5" vspace="29">
<img src="https://user-images.githubusercontent.com/13310795/27508803-8d617fbc-58ed-11e7-873d-0f0c62002d1a.png" width="200" hspace="5">
<img src="https://user-images.githubusercontent.com/13310795/27508804-8d62a450-58ed-11e7-8509-22d377db8637.png" width="200" hspace="5">
</div>