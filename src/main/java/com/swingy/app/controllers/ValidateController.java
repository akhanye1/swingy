/*
 * Author Katleho Khanye (akhanye)
 * */

package	com.swingy.app.controllers;

import javax.validation.*;
import javax.validation.constraints.*;
import javax.validation.executable.*;
import org.hibernate.validator.*;
import org.hibernate.*;
import com.swingy.app.models.ValidationErrorModel;
import java.util.List;
import java.util.ArrayList;
import com.swingy.app.models.PlayerModel;
import java.util.Set;

public class ValidateController {
	private static Validator	validator;

	public ValidateController() {
		final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	public static boolean runValidator(List<ValidationErrorModel> errors, PlayerModel player) {
		boolean ok;

		ok = true;
		Set<ConstraintViolation<PlayerModel>> validationErrors = validator.validate(player);
		if (!validationErrors.isEmpty()) {
			ok = false;
			for (ConstraintViolation<PlayerModel> error : validationErrors) {
				ValidationErrorModel tempModel = new ValidationErrorModel(error.getPropertyPath().toString(), error.getMessage());
				errors.add(tempModel);
			}
		}
		return (ok);
	}
}
