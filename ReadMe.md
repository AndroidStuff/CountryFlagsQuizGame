# Country Flags Quiz Game

## Features Included
* Included View-Binding using [ButterKnife](http://jakewharton.github.io/butterknife/), whose jar can be downloaded from [here](https://search.maven.org/remote_content?g=com.jakewharton&a=butterknife&v=LATEST).
* This project uses ButterKnife version 7.0.1

## User Stories Pending
* Count the number of questions answered correctly on the first try differently and display total results after all questions have been answered.
* Create a "Bonus Question" feature asking for the country's capital after the user guesses the country. The user should only get one guess for the bonus. If correct, add 10 points to the user's score, otherwise, display the correct capital.

## References/Inspiration
* [Recommended Book] Android For Programmers - An App-Driven Approach by Deitel & Associates Inc
* Sample source code looked upon for idea and inspiration includes: 
    -- https://github.com/MobileApps-Cascadia/HTP-chapter05
    -- https://github.com/rnfsoft/FlagQuiz
* Free Country Flag images can be downloaded from any of the following sources
    -- http://www.free-country-flags.com/ (I downloaded from this site for the purpose of this project)
    -- http://flagpedia.net/download
    -- Google for many other sites ;)
* If you get NPE (Null Pointer Exception) when using ButerKnife, the first check should be to see if AnnotationProcessing is enabled in your IDE and that this library reference exists for it there per the [ButterKnife Docs](http://jakewharton.github.io/butterknife/ide-eclipse.html)
* ButterKnife Docs can be found [here](http://jakewharton.github.io/butterknife/).
* For detailed local wiki, that is shamelessly copy-pasted from [CodePath's Wiki](https://github.com/codepath/android_guides/wiki/Reducing-View-Boilerplate-with-Butterknife), go to ReadMe-ButterKnife.md file of this project      
