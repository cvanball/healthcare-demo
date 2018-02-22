#!/bin/bash
rm -rf p360_output/alerts.csv
cd p360
sbt  "run teiidUser redhat1! jdbc:teiid:RTDS@mm://localhost:31000 PATIENT360.PATIENT"
cd ..
find . -name "part*" -exec sh -c 'mv "$1" "${1%.gappedPeak}.csv"' _ {} \;
