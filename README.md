# Shop API

[![Greenkeeper badge](https://badges.greenkeeper.io/reflexdemon/shop.svg)](https://greenkeeper.io/)

[![Build Status][travis-image]][travis-url]

This is a carlessly written simple API that is implemented using [Spring Boot] and some of its associated project. The running application has been hosted on the [Open Shift(r) RedHat(r) Cloud][open shift]. [Spring Boot] is a nice little handy framework that I have workd with and it is giving us the freedom to build rapid and robust applications. This project was mainly created to learn the following concepts in around [Spring Boot] and [Angular 2].

Please note this was built in the intentions of learning new stuff and not to code for production. At the same time there was utmost care teken to keep this up-to-date with the latest and greatest and acts as a reference implementation to club all pollible things that has been learnt.


# Contributions

I stongly welcome all the developers to feel free to fork the project and send me pull requests to help me keep this molnolythic web application up and running.

# Tech Stack

The Tech Stack can be broadly grouped into 3 distinct areas of intrest.

1. Backend/Server
2. Frontend/UI
3. Deployment/Cloud

## 1. Backend/Server
This out and out uses Java based technologies like [Spring Boot] and [Java 8]. This is just a combination of Framworks/Technologies/Features that are listed below are used in this project.

I am excited to let you know that we are currently on `Spring Boot 2.0.M3`

1. [Java 8]
2. [Spring]
2. [Swagger]
2. [Spring Testing]
3. [Spring Security]
4. [Spring Boot MVC Jackson]
5. [JMS consumer and producer using Apache ActiveMQ]
6. [Spring Boot Web] Templating using [Thymeleaf]
7. [Spring AOP]
8. [Spring Mongo DB]


## 2. Frontend/UI
 1. [Angular 2]
 2. [Angular CLI]
 3. [Material Design]
 4. [Webpack]
 5. [Yarn] / [npm]
 
 
## 3. Deployment/Cloud
1. [Open Shift] (r) [DIY] Cartige

# Index Page
This is more in construction phase as I was busy building the spring based backend API.
Now time to explore the [Angular 2] page and can expect a glaring page soon :)

# API Login

Please [signup] to gain access.

# Building

## UI
Before you push any changes that you made in ui project please ensure to run the below to generate the ui artifacts.
```
yarn run build
```
## Backend
Once the above is built, please do the following task to run generate the `war` file.

```
mvn clean package -P openshift -DskipTests=true
```


# Running

## Localhost only

### API Service

Run the Java process on [Tomcat] or you can run the [Spring boot] app's `org.shop.Application.main()` method.
Lets assume it is running on `localhost:8080`. This will be our API service.

### Angular WebApp

To run the Angular WebApp with local proxy please run the below.

#### Yarn
```
$ yarn install    #Only once!
$ yarn run proxy
```

#### NPM
```
$ npm install    #Only once!
$ npm run proxy
```

## Remote API

### API Service

You can relay on the running version of the API on the [Open Shift] cloud and it is not required to run in your `localhost`.

### Angular WebApp

To run the Angular WebApp with local proxy please run the below.

#### Yarn
```
$ yarn install    #Only once!
$ yarn run proxyr
```

#### NPM
```
$ npm install    #Only once!
$ npm run proxyr
```

As a recommendation for running the applicaiton locally for development purpose, I would strongly recommend running with remote proxy.


# Thanks
Thanks to [Baron Wilson's][Baron Wilson] [Angular 2 Starter seed][Angular Starter].



>**_Disclaimer_**: I am using a free version of the [Open Shift] gear and if not accessed for more then 24hrs they will shutdown the cartige. In case you see the site to be down please click on http://shop-venkatvp.rhcloud.com/index.html and the site will start up by itself. This will bring all the services back into action in few minutes.




[spring boot]: http://projects.spring.io/spring-boot/
[open shift]: https://www.openshift.com/
[Spring Testing]: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
[Swagger]: http://swagger.io/
[Spring Security]: https://projects.spring.io/spring-security/
[Spring Boot MVC Jackson]: http://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html
[JMS consumer and producer using Apache ActiveMQ]:https://spring.io/guides/gs/messaging-jms/
[Thymeleaf]:https://spring.io/guides/gs/serving-web-content/
[Spring AOP]: https://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html
[Spring Mongo DB]: https://spring.io/guides/gs/accessing-data-mongodb/
[Spring Boot Web]: https://spring.io/guides/gs/spring-boot/
[Angular 2]: https://angular.io/
[Angular CLI]: https://cli.angular.io/
[DIY]: https://github.com/openshift/origin-server/blob/master/documentation/oo_cartridge_guide.adoc#diy
[swagger api]: http://shop-venkatvp.rhcloud.com/swagger-ui.html
[Web Page]: http://shop-venkatvp.rhcloud.com/index.html
[Angular Starter]: https://github.com/thebaron24/angular-webapp
[Baron Wilson]: https://github.com/thebaron24
[Bootstrap 4]: https://v4-alpha.getbootstrap.com/
[Material Design]: https://material.angular.io/
[Webpack]: https://webpack.js.org/
[Yarn]: https://yarnpkg.com/
[Java 8]: http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html
[TypeScripts]: https://www.typescriptlang.org/
[Spring]: http://spring.io
[Tomcat]: http://tomcat.apache.org/
[npm]: https://www.npmjs.com/
[signup]: [http://shop-venkatvp.rhcloud.com/#/signup]
[travis-image]: https://travis-ci.org/reflexdemon/shop.svg?branch=master
[travis-url]: https://travis-ci.org/reflexdemon/shop