/* (C)2024 */
package fr.jozait.kanban.validator;

import java.util.Arrays;
import java.util.List;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public class PasswordConstraintsValidator {

  private static final int MIN_CHARACTERS = 8;

  private static final int MAX_CHARACTERS = 24;

  public static List<Rule> RULES =
      Arrays.asList(
          // Length rule. Min 10 max 128 characters
          new LengthRule(MIN_CHARACTERS, MAX_CHARACTERS),

          // At least one upper case letter
          new CharacterRule(EnglishCharacterData.UpperCase, 1),

          // At least one lower case letter
          new CharacterRule(EnglishCharacterData.LowerCase, 1),

          // At least one number
          new CharacterRule(EnglishCharacterData.Digit, 1),

          // At least one special characters
          new CharacterRule(EnglishCharacterData.Special, 1),
          new WhitespaceRule());

  public static RuleResult validate(PasswordValidator validator, String password) {

    return validator.validate(new PasswordData(password));
  }

  public static String getMessage(PasswordValidator validator, RuleResult result) {
    return validator.getMessages(result).getFirst();
  }
}
