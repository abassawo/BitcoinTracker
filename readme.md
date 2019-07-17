# Bitcoin Tracker

1. [Overview](#overview)
1. [Cloning the Project](#cloning-the-project) 
1. [Building](#building)
1. [Testing](#testing)
    1. [Unit Tests](#unit-tests)
1. [Architecture](#architecture)
    1. [Model-View-Presenter](#model-view-presenter)
    1. [Base Classes](#base-classes)
    1. [Third Party Libraries](#third-party-libraries)

## Overview
The project contains the following components:
-   Ktx (Kotlin extensions for view binding) and Android Design Library
-   MPAndroidChart for displaying charts
-   Commonly used third party dependencies (RxJava, retrofit, etc))
-   Base MVP framework
-   Other setups to support performance and dependency injection (dagger, custom Application, Timber, etc)

## Cloning the project
To check out the source, simply download/clone the repo 

## Building
This project does not require any additional setup or special configurations to build or run.

### Configurations
- There are 2 different build types - Debug, and Release. Main difference between the two is Debug build is eligible for logging via timber. Release build will also require generated keystore file.

## Testing
### Unit Tests
Unit tests exist under the "test" directory. 

## Architecture
### Model-View-Presenter
The app uses the popular MVP architecture to allow for separation of logic and ease of testing. In this paradigm, all business logic should live inside presenters (but they can delegate some tasks to other classes that are injected as dependencies). Activities and fragment will act as "views", they should not have any logic other than passing the user events to the presenter and displaying the data. There are also Contract classes that specify the communication interface between the views and presenters.  

### Base Classes
- `BaseMvpActivity`: Base class for activities. Includes setup for interactions with presenter.
- `BaseMvpFragment`: Basically the same as `BaseMvpActivity`, but for fragments.
- `BasePresenter`: Base class for all presenters. Includes lifecycle setup and common dependencies. Goes along with `BaseMvpActivity` and `BasMvpFragment`.
- `BaseContract`: Includes interfaces that all views and presenters should implement.

### Third Party Libraries
- RxJava/RxAndroid (helps with asynchronous event handling)
- Retrofit/OkHttp (simplifies network requests)
- Timber (better logging tool)
- Mockito (mocks out classes for tests)
