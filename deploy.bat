call ant
copy .\dist\TeamCityCiGame.zip C:\ProgramData\JetBrains\TeamCity\plugins  /Y
call %TEAMCITY_HOME%\buildAgent\bin\agent.bat stop
CHOICE /C:AB /D:A /T:2 >NUL
call %TEAMCITY_HOME%\buildAgent\bin\agent.bat start