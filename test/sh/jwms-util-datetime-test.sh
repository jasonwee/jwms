#!/usr/bin/env bash

source ../../src/sh/jwms-util-datetime.sh

res=`second_to_dhms`
if [ "$res" != "1 days 00 hours 00 minutes 00 seconds" ]; then
  echo "FAIL: second_to_dhms $res"
  exit 1
else
  echo -n "."
fi

res=`total_time 1475510400 1475596800`
if [ "$res" != "86400" ]; then
  echo "FAIL: total_time $res"
  exit 1
else
  echo -n "."
fi

res=`total_time_in_ms 1475510400 1475596800`
if [ "$res" != "86400000" ]; then
  echo "FAIL: total_time_in_ms $res"
  exit 1
else
  echo -n "."
fi
