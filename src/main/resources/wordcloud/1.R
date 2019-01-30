library(jiebaRD)
library(jiebaR)
library(wordcloud2)
composition<-readLines(con = "F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.txt",encoding = "utf-8")
wk<-worker(stop_word = "F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\2.txt")
words<-segment(composition,wk)
words<-table(words)
words[order(words,decreasing = TRUE)]
words<-data.frame(words)
 words[,1]<-as.character(words[,1])
words<-words[-which(nchar(words[,1])<2),]
words<-words[order(words[,2],decreasing = T),]
picture<-wordcloud2(words[1:5,])
htmlwidgets::saveWidget(picture,file = "F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.html",selfcontained = FALSE)