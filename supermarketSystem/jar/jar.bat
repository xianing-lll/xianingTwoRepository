@echo off
SET javaw="%JAVA_HOME%\bin\javaw"
start java -jar "sms_server_eureka-0.0.1-SNAPSHOT.jar" 
start java -jar "sms_server_gateway-0.0.1-SNAPSHOT.jar" 
start java -jar "sms_server_auth2-0.0.1-SNAPSHOT.jar" 
start java -jar "sms_server_staff-0.0.1-SNAPSHOT.jar" 
start java -jar "sms_server_cash_register-0.0.1-SNAPSHOT.jar" 
start java -jar "sms_server_trade-0.0.1-SNAPSHOT.jar" 
exit