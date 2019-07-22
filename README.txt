This is a basic webcrawlerprogram that takes a url and a desired depth, and searches thorugh all available anchors on the original page.

It returns the title and meta tags on all pages it crawls, and prints all useful unformation to the command line.

I decided to use the Jsoup html parsing library.

This repository contains an eclipse project, using jdk-11.0.2

The eclipse project is supplied with default command line options of "https://wikipedia.org 2", as an example of the expected format of input.