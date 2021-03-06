/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dia.algorithms.mcs

object MainNetcdfDFSMCS {

  val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  /**
   * Input arguments to the program :
   * args(0) - the spark master URL. Example : spark://HOST_NAME:7077
   * args(1) - the number of desired partitions. Default : 2
   * args(2) - path to files
   *
   * @param args the arguements
   * @return succesful parsing
   */
  def processCmd(args: Array[String]): (String, String, String, Int, String) = {
    val masterURL = if (args.isEmpty) "local[*]" else args(0)
    val partitions = if (args.length <= 1) 8 else args(1).toInt
    val path = if (args.length <= 2) "resources/paperSize/" else args(2)
    val varName = if (args.length <= 3) "ch4" else args(3)
    val outputDir = if (args.length <= 4) "output" else args(4)
    (masterURL, path, varName, partitions, outputDir)
  }

  def main(args: Array[String]): Unit = {

    /**
     * Process cmd line arguements
     */
    val (masterURL, path, varName, partitions, outputDir) = processCmd(args)

    /**
     * Run MCS search with GTGRunner
     */
    val gTGRunner = new GTGRunner(masterURL, path, varName, partitions, outputDir)
    gTGRunner.run()

  }

}
