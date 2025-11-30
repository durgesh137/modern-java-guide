## Possible Errors

java: java.lang.ExceptionInInitializerError
com.sun.tools.javac.code.TypeTag :: UNKNOWN

this error appears when there is a mismatch between the Java version used to compile the code and the Java version used to run it. 
Ensure that you are using the same Java version for both compiling and running your application.

Downloading JDKs with intellij UI
Using intellij SDK manager to Download JDKs
File | Project Structure... (or ⌘;) -> Platform Settings → SDKs -> click + -> Download JDK...
In the dialog pick distribution (Temurin, JetBrains, Corretto, etc.), Java version (8, 11, 17, 21...), architecture and click Download.

Repeat for each Java version you need. Downloaded JDKs appear under SDKs.
Set project/module SDK (optional)

File | Project Structure... -> Project -> pick Project SDK.
For module-specific: Modules -> select module -> Module SDK.
Pick a JDK when running
Run | Edit Configurations... -> select your configuration (Application, JUnit, Maven, etc.) -> choose the JRE / JDK dropdown and pick the installed JDK.
For Maven runs: edit the Maven run configuration and set JRE.
For Gradle: Preferences | Build, Execution, Deployment | Build Tools | Gradle -> Gradle JVM

Get details error log with maven
mvn -e -X

No goals specified error
No goals have been specified for this build. You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: pre-clean, clean, post-clean, validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-site, site, post-site, site-deploy. -> [Help 1]
org.apache.maven.lifecycle.NoGoalSpecifiedException: No goals have been specified for this build. You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: pre-clean, clean, post-clean, validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-site, site, post-site, site-deploy.                                                                                                                                                        
at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:91)
at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:261)
at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:173)
at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:101)

mvn clean package
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) on project java8: Fatal error compiling: invalid flag: --release -> [Help 1]

Error
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) on project java12: Fatal error compiling: error: release version 12 not supported -> [Help 1]

Rather than downloading and installing multiple JDKs prior to start working, 
comment modules within main pom.xml that are not needed for current work.