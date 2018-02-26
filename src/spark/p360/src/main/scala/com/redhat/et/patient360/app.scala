package com.redhat.et.patient360;

import com.redhat.et.silex.app.AppCommon

object QueryApp extends AppCommon {
  override def appName = "patient360-demo"
  
  // the args to main should be the JDBC url and the table name
  override def appMain(args: Array[String]) {
    require(args.length == 4, "usage:  QueryApp USERNAME PASSWORD JDBC_URL TABLE_NAME")
    val source = sqlContext.read.format("jdbc").options(Map("user" -> args(0), "password" -> args(1),"url" -> args(2),"dbtable" -> args(3))).load()
    val results = Queries.bpWarnings(source)
    val outputDir = System.getProperty("spark.output.dir","default")

    results.show(results.count.toInt, false)
    results.write.format("com.databricks.spark.csv").save(outputDir + "/alerts.csv")
  }
}
