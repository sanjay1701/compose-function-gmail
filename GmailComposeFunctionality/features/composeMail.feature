Feature: Compose Functionality of Gmail 

@TC-001(Minimize,Maximize,CloseMailWindow)
Scenario: Verification of Compose function 

Given User lands on Gmail Homepage 
When User clicks on Maximize button, Minimize button and Close button on Compose Mail window
Then Compose Mail Window should be closed

@TC-002(MailSentWithoutSubjectAndBody)
Scenario: Accept pop up when mail sent without subject and body

Given User lands on Gmail Homepage and clicks on Compose button
When User enters multiple/single gmail/non-gmail id in recipents text and Clicks on Send Button
Then User is displayed with popup message and he accepts the message and mail should be sent


@TC-003(FileAttachment)
Scenario: Attach valid files to email to a maximum of 25mb after that user get pop up to attach as drive link

Given  User clicks on Compose button and enters multiple/single gmail/non-gmail id in recipents, subject and body
When User clicks on Attach File icon and attaches file 
And when size increases 25mb then user gets a pop up to attach file as drive link 
Then User clicks on Cancel button and clicks on Send button
Then Verify that mail should be present in Sent Items sections

@TC-004(ErrorUnsupportedFileAndDiscard)
Scenario: Error message for unsupported file type and discard the mail

Given User clicks on Compose button and enter recipents, subject and body
When User clicks on Attach File icon and attaches unsupported file
Then User gets error message and discards the mail

@TC-005(InsertLink)
Scenario: Insert link in body section of email

Given User clicks on Compose and enter recipents
When User clicks on Insert Link and attaches external link to mail
Then User sends the mail and mail is sent

@TC-006(InsertEmoticon)
Scenario: Insert Emoticons to mail

Given User clicks on Compose button then new mail window is launched
When User clicks Insert Emoji icon and attaches emoticons
Then User closes the mail window and mail is saved to Drafts and count is incremented

@TC-007(WithoutRecipent)
Scenario: Mail without Recipent

Given User clicks on Compose and a new mail window is opened
When User clicks on Send Button after entering subject and body
Then User gets pop up message to specify recipent
