package org.dia.tensors

import org.nd4j.linalg.factory.Nd4j
import org.scalatest.FunSuite

class ComparisonTest extends FunSuite {
  val ArraySample = Array(
    Array(0.0, 1.0, 0.0, 1.0, 1.0),
    Array(0.0, 1.0, 0.0, 2.0, 1.0),
    Array(2.0, 3.0, 4.0, 1.0, 2.0),
    Array(0.0, 1.0, 0.0, 1.0, 1.0),
    Array(0.0, 1.0, 0.0004003492849200234294, 2.0, 1.0),
    Array(2.0, 3.0, 4.0, 1.0, 2.0)
  )
  val sample = Nd4j.create(ArraySample)
  val shapePair = (sample.data.asDouble, sample.shape)

  test("Testing Equality") {
    val nd4j: AbstractTensor = new Nd4jTensor(shapePair)
    val breeze: AbstractTensor = new BreezeTensor(shapePair)
    assert(nd4j.rows == breeze.rows)
    assert(nd4j.cols == breeze.cols)
    assert(nd4j == breeze)
  }
}