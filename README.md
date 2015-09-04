# Drools Attributes
This maven based project showcases how to inspect a compiled set of rules (a `KieBase`) in order to determine some characteristics of the rule sets.

## Domain Class
The `com.rhc.drools.example.model.Person` class is a Plain Old Java Object (POJO) that is used in our rules. It has two attributes, a name and age.

## Rule file
The `name.drl` file contains the rules we will be inspecting. Feel free to change the rule setting Sal's age as that is the rule that will be analyzed.

## Getting the Kie Base
The `com.rhc.drools.example.kie.KieBaseProvider` class will create a Classpath `KieContainer` and produce a `KieBase` using it.

## Attributes
The `com.rhc.drools.example.Attributes` class contains the main entry point. After imporing this project into you favorite IDE running this class will inspect the rule and print out any classes and fields used.
