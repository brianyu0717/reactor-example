# Reactive programming with Reactor demo

## Module ```pure```

Unit test to demonstrate basic reactor usage.

## Module ```spring-app```

A Spring web app with two endpoints:

1. ```/message-blocking/{message}``` blocks the thread processing the current request
2. ```/message-reactive/{message}``` returns reactive ```Mono```, does not block any threads

## Module ```spring-webclient-app```

A Spring web app with two endpoints:

1. ```/flux-blocking/{times}``` calls the blocking endpoint in ```spring-app``` ```times``` many times.
2. ```/flux-reactive/{times}``` calls the reactive endpoint in ```spring-app``` ```times``` many times.

### For science, we observe

* ```/flux-blocking/100``` takes 20s to return
* ```/flux-reactive/100``` takes 2s to return

Isn't event loops great?
