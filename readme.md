# File cleaning procedure

_The cleaning procedure deletes old files from the folder._

### Using
 You need to specify the path to the folder for the monitor and the period of saving files.
 You must run the jar file in the terminal with this command:
 
  `java -jar filecleaning.jar [folder] [period]`
  
  - `folder` - the path of the folder you want to monitor
  - `period` - file saving period.
             
             integer - the number of months from the current time to delete files.
             
             negative number - delete all files in the folder.
             
             0 - do not delete files.