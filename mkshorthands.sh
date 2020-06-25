#!/bin/bash

grep -RHF @ShorthandName src |
	grep -vE '^Binary file ' |
	sed -r 's#^src/([A-Za-z0-9_/]+)\.java:@ShorthandName\("([^"]+)"\)$#%alias \2 = \1#;s@/@.@g' |
	sort \
	>res/shorthands.ogdl
