#!/bin/bash
OUTPUT_DIR=$1

rm -rf ${OUTPUT_DIR}/alerts.csv
cd p360
sbt -Dspark.output.dir=${OUTPUT_DIR} "run teiidUser redhat1! jdbc:teiid:RTDS@mm://localhost:31000 PATIENT360.PATIENT" 

cd ${OUTPUT_DIR}/..
find . -name "part*" -exec sh -c 'mv "$1" "${1%.gappedPeak}.csv"' _ {} \;