
import org.scalatest._

class GnipRuleParserSpec extends WordSpec with MustMatchers with TryValues {

  "Gnip rule parser" should {
    "accept single character" in {
      GnipRuleParser("h").success
    }
    "accept single word" in {
      GnipRuleParser("hello").success
    }
    "accept multiple words" in {
      GnipRuleParser("hello? beautiful world!").success
    }
    "accept multiple words with negation" in {
      GnipRuleParser("hello? -beautiful world! -foo -bar bla -lol").success
    }
    "accept quoted word" in {
      GnipRuleParser("\"hello\"").success
    }
    "accept quoted words" in {
      GnipRuleParser("\"hello world!\"").success
    }
    "accept all combinations of optional negation and quoted words" in {
      GnipRuleParser("\"hello world?\" bla -bla \"lol!\" bla").success
    }
    "not accept single negated word" in {
      GnipRuleParser("-hello").failure
    }
    "not accept only negated words" in {
      GnipRuleParser("-hello -world").failure
    }
    "not accept quoted negated words" in {
      GnipRuleParser("\"-hello world\"").failure
    }
    "not accept unfinished quotes" in {
      GnipRuleParser("\"-hello world\" bla \"lol bla bla").failure
    }
    "not accept empty string" in {
      GnipRuleParser("").failure
    }
    "not accept single stop word" in {
      GnipRuleParser("the").failure
    }
    "not accept only stop words" in {
      GnipRuleParser("a an and at but by com from http https if in is it its me my or rt the this to too via we www you").failure
    }
  }

}
