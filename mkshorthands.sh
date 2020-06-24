#!/bin/bash

grep -RHF @ShorthandName src |
	sed -r 's#^src/([A-Za-z0-9_/]+)\.java:@ShorthandName\("([^"]+)"\)$#%alias \2 = \1#;s@/@.@g' \
	>res/shorthands.ogdl
