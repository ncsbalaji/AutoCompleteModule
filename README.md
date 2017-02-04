# AutoCompleteModule

Developed on Tomcat Sever V9
Used Jackson and Jersy API for json mapping

=> Rest URL Format and inputs:

http://localhost:8080/AutoSuggest/suggest/query?prefix={prefix}&isEnd={boolean}

1.	To get all suggestions pass any prefix in {prefix} and isEnd = false

2.	To validate entry from user ie., if user press Enter Key pass isEnd = true


=> Logic of Trie implementation in our Prefix Based auto suggestions

	For every prefix user enter, we find the matched Trie node by traversing the prefix

	Get all the children of the matched node recursively which are complete word and populate auto complete suggestion (can be improved further if number of suggestions are limited)

	If user further enter a character then the search is further narrowed deep in the trie structure which in return improves efficiency
Assumptions

	No limit on number of suggestions, currently showing all filtered autocomplete suggestions. If limit imposed, it will improve further efficiency of the algorithm and usability

	Special characters are not neglected in auto suggestions

	JSON response format from rest service is assumed since UI validations are mostly done using Java Script which understands JSON easily than XML



