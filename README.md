# java_email_blaster
A java application made for blasting out emails to many other reciepients. 

# How to use it ?

Download the files by cloning it from this repository. Be sure to have java8 installed in your system. Run it in your preferred IDE and your good to go.


# Any dependencies ?

There are a few jar files that you will need to add to your java extension. For sending email across, use API JavaMail. Download it at https://javaee.github.io/javamail/ . Next is the javax activation jar file which you could get it from here at https://mvnrepository.com/artifact/javax.activation/activation/1.1.1 .  For the handling of csv , opencsv is used http://opencsv.sourceforge.net/ . Be sure to add them to your classpath, otherwise this program will not work. You could also add them to the lib/ext file in both jre and jdk files.


# How to structure you csv so that it can be read into the program?

The first column is the name of the institution, second is the PIC name and third column is the email contact. If contacts in that row is empty it will skip it.


# Any bugs ?

Still working on improving it. Let me know if there is anyway I could try to improve it. 
