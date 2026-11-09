import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class RelativeDistanceTest extends AnyFunSuite with Matchers:

  test("Direct parent-child relation"):
    val familyTree = Map("Vera" -> List("Tomoko"), "Tomoko" -> List("Aditi"))
    RelativeDistance(familyTree).degreeOfSeparation("Vera", "Tomoko") should be(Some(1))

  test("Sibling relationship"):
    pending
    val familyTree = Map("Dalia" -> List("Olga", "Yassin"))
    RelativeDistance(familyTree).degreeOfSeparation("Olga", "Yassin") should be(Some(1))

  test("Two degrees of separation, grandchild"):
    pending
    val familyTree = Map("Khadija" -> List("Mateo"), "Mateo" -> List("Rami"))
    RelativeDistance(familyTree).degreeOfSeparation("Khadija", "Rami") should be(Some(2))

  test("Unrelated individuals"):
    pending
    val familyTree = Map("Priya" -> List("Rami"), "Kaito" -> List("Elif"))
    RelativeDistance(familyTree).degreeOfSeparation("Priya", "Kaito") should be(None)

  test("Complex graph, cousins"):
    pending
    val familyTree = Map(
      "Aiko"    -> List("Bao", "Carlos"),
      "Bao"     -> List("Dalia", "Elias"),
      "Carlos"  -> List("Fatima", "Gustavo"),
      "Dalia"   -> List("Hassan", "Isla"),
      "Elias"   -> List("Javier"),
      "Fatima"  -> List("Khadija", "Liam"),
      "Gustavo" -> List("Mina"),
      "Hassan"  -> List("Noah", "Olga"),
      "Isla"    -> List("Pedro"),
      "Javier"  -> List("Quynh", "Ravi"),
      "Khadija" -> List("Sofia"),
      "Liam"    -> List("Tariq", "Uma"),
      "Mina"    -> List("Viktor", "Wang"),
      "Noah"    -> List("Xiomara"),
      "Olga"    -> List("Yuki"),
      "Pedro"   -> List("Zane", "Aditi"),
      "Quynh"   -> List("Boris"),
      "Ravi"    -> List("Celine"),
      "Sofia"   -> List("Diego", "Elif"),
      "Tariq"   -> List("Farah"),
      "Uma"     -> List("Giorgio"),
      "Viktor"  -> List("Hana", "Ian"),
      "Wang"    -> List("Jing"),
      "Xiomara" -> List("Kaito"),
      "Yuki"    -> List("Leila"),
      "Zane"    -> List("Mateo"),
      "Aditi"   -> List("Nia"),
      "Boris"   -> List("Oscar"),
      "Celine"  -> List("Priya"),
      "Diego"   -> List("Qi"),
      "Elif"    -> List("Rami"),
      "Farah"   -> List("Sven"),
      "Giorgio" -> List("Tomoko"),
      "Hana"    -> List("Umar"),
      "Ian"     -> List("Vera"),
      "Jing"    -> List("Wyatt"),
      "Kaito"   -> List("Xia"),
      "Leila"   -> List("Yassin"),
      "Mateo"   -> List("Zara"),
      "Nia"     -> List("Antonio"),
      "Oscar"   -> List("Bianca"),
      "Priya"   -> List("Cai"),
      "Qi"      -> List("Dimitri"),
      "Rami"    -> List("Ewa"),
      "Sven"    -> List("Fabio"),
      "Tomoko"  -> List("Gabriela"),
      "Umar"    -> List("Helena"),
      "Vera"    -> List("Igor"),
      "Wyatt"   -> List("Jun"),
      "Xia"     -> List("Kim"),
      "Yassin"  -> List("Lucia"),
      "Zara"    -> List("Mohammed"),
    )
    RelativeDistance(familyTree).degreeOfSeparation("Dimitri", "Fabio") should be(Some(9))

  test("Complex graph, no shortcut, far removed nephew"):
    pending
    val familyTree = Map(
      "Aiko"    -> List("Bao", "Carlos"),
      "Bao"     -> List("Dalia", "Elias"),
      "Carlos"  -> List("Fatima", "Gustavo"),
      "Dalia"   -> List("Hassan", "Isla"),
      "Elias"   -> List("Javier"),
      "Fatima"  -> List("Khadija", "Liam"),
      "Gustavo" -> List("Mina"),
      "Hassan"  -> List("Noah", "Olga"),
      "Isla"    -> List("Pedro"),
      "Javier"  -> List("Quynh", "Ravi"),
      "Khadija" -> List("Sofia"),
      "Liam"    -> List("Tariq", "Uma"),
      "Mina"    -> List("Viktor", "Wang"),
      "Noah"    -> List("Xiomara"),
      "Olga"    -> List("Yuki"),
      "Pedro"   -> List("Zane", "Aditi"),
      "Quynh"   -> List("Boris"),
      "Ravi"    -> List("Celine"),
      "Sofia"   -> List("Diego", "Elif"),
      "Tariq"   -> List("Farah"),
      "Uma"     -> List("Giorgio"),
      "Viktor"  -> List("Hana", "Ian"),
      "Wang"    -> List("Jing"),
      "Xiomara" -> List("Kaito"),
      "Yuki"    -> List("Leila"),
      "Zane"    -> List("Mateo"),
      "Aditi"   -> List("Nia"),
      "Boris"   -> List("Oscar"),
      "Celine"  -> List("Priya"),
      "Diego"   -> List("Qi"),
      "Elif"    -> List("Rami"),
      "Farah"   -> List("Sven"),
      "Giorgio" -> List("Tomoko"),
      "Hana"    -> List("Umar"),
      "Ian"     -> List("Vera"),
      "Jing"    -> List("Wyatt"),
      "Kaito"   -> List("Xia"),
      "Leila"   -> List("Yassin"),
      "Mateo"   -> List("Zara"),
      "Nia"     -> List("Antonio"),
      "Oscar"   -> List("Bianca"),
      "Priya"   -> List("Cai"),
      "Qi"      -> List("Dimitri"),
      "Rami"    -> List("Ewa"),
      "Sven"    -> List("Fabio"),
      "Tomoko"  -> List("Gabriela"),
      "Umar"    -> List("Helena"),
      "Vera"    -> List("Igor"),
      "Wyatt"   -> List("Jun"),
      "Xia"     -> List("Kim"),
      "Yassin"  -> List("Lucia"),
      "Zara"    -> List("Mohammed"),
    )
    RelativeDistance(familyTree).degreeOfSeparation("Lucia", "Jun") should be(Some(14))

  test("Complex graph, some shortcuts, cross-down and cross-up, cousins several times removed, with unrelated family tree"):
    pending
    val familyTree = Map(
      "Aiko"    -> List("Bao", "Carlos"),
      "Bao"     -> List("Dalia"),
      "Carlos"  -> List("Fatima", "Gustavo"),
      "Dalia"   -> List("Hassan", "Isla"),
      "Fatima"  -> List("Khadija", "Liam"),
      "Gustavo" -> List("Mina"),
      "Hassan"  -> List("Noah", "Olga"),
      "Isla"    -> List("Pedro"),
      "Javier"  -> List("Quynh", "Ravi"),
      "Khadija" -> List("Sofia"),
      "Liam"    -> List("Tariq", "Uma"),
      "Mina"    -> List("Viktor", "Wang"),
      "Noah"    -> List("Xiomara"),
      "Olga"    -> List("Yuki"),
      "Pedro"   -> List("Zane", "Aditi"),
      "Quynh"   -> List("Boris"),
      "Ravi"    -> List("Celine"),
      "Sofia"   -> List("Diego", "Elif"),
      "Tariq"   -> List("Farah"),
      "Uma"     -> List("Giorgio"),
      "Viktor"  -> List("Hana", "Ian"),
      "Wang"    -> List("Jing"),
      "Xiomara" -> List("Kaito"),
      "Yuki"    -> List("Leila"),
      "Zane"    -> List("Mateo"),
      "Aditi"   -> List("Nia"),
      "Boris"   -> List("Oscar"),
      "Celine"  -> List("Priya"),
      "Diego"   -> List("Qi"),
      "Elif"    -> List("Rami"),
      "Farah"   -> List("Sven"),
      "Giorgio" -> List("Tomoko"),
      "Hana"    -> List("Umar"),
      "Ian"     -> List("Vera"),
      "Jing"    -> List("Wyatt"),
      "Kaito"   -> List("Xia"),
      "Leila"   -> List("Yassin"),
      "Mateo"   -> List("Zara"),
      "Nia"     -> List("Antonio"),
      "Oscar"   -> List("Bianca"),
      "Priya"   -> List("Cai"),
      "Qi"      -> List("Dimitri"),
      "Rami"    -> List("Ewa"),
      "Sven"    -> List("Fabio"),
      "Tomoko"  -> List("Gabriela"),
      "Umar"    -> List("Helena"),
      "Vera"    -> List("Igor"),
      "Wyatt"   -> List("Jun"),
      "Xia"     -> List("Kim"),
      "Yassin"  -> List("Lucia"),
      "Zara"    -> List("Mohammed"),
    )
    RelativeDistance(familyTree).degreeOfSeparation("Wyatt", "Xia") should be(Some(12))
