jScalp
=======

Java implementation of an Apache log analyzer, inspired by Scalp!

Scalp is an awesome tool that uses PHPIDS filters to parse Apache access logs. We needed a similar
tool that could be run in a pure Java environment (yeah, I know, I know), so jScalp was created.
jScalp will read an XML filter file and run the filters against an Apache Combined Format access log.

Usage: java -jar jscalp.jar modified_filters.xml access.log

The filter XML should be formatted similarly to the PHPIDS filter XML.
The access log should be formatted according to the Apache Combined Log Format.

The IDS filters utilize conditionals, which are not supported by Java's default regular expression engine. 
Because of this, jScalp utilizes the JRegex library (http://jregex.sourceforge.net/). 
JRegex is released under the BSD license, which is reproduced in JREGEX_LICENSE.
If you are planning on building from source, you will need to add the JRegex jar to your build path.

A sample filter file is included (modified_filters.xml). These filters are modified versions of the filters
found in the PHPIDS project (https://github.com/PHPIDS/), released under the GNU LGPL license, which is
reproduced in PHPIDS_LICENSE.
The modifications consist of character escapings required by Java to compile the regular expressions.
