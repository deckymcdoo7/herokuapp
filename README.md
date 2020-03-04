Prerequisite
- chromedriver installed via brew. brew cask install chromedriver
- geckodriver installed via brew install geckodriver

# How to Run
- From terminal navigate to project root directory 
- run mvn install -Dmaven.test.skip=true (This will insall dependencies without running tests)
- Run mvn test (you can run with system property -Dbrowser='browser name i.e. firefox chome')  if this is not passed in we will default to running on chrome browser

#CI updates
- There is a jenkins file included in the project root folder.
This is read by jenkins and a webhook is setup to trigger each a build
each PR. To updates jenkins job directly make changes to jenkinsfile and push to github

#Issues
- As only computer available to me was a mac I was unable to 
run the IE tests. I have detailed in the Test plan which tests
I would write if I had access to saucelabs | pc 
