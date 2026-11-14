import ZebraPuzzle.*

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class ZebraPuzzleTest extends AnyFunSuite with Matchers:

  test("resident who drinks water"):
    ZebraPuzzle.solve.waterDrinker should be(Norwegian)

  test("resident who owns zebra"):
    pending
    ZebraPuzzle.solve.zebraOwner should be(Japanese)
