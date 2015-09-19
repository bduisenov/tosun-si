package org.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Mazlum
 */
public class Validator<T> {

  private final T t;
  private final List<Throwable> exceptions = new ArrayList<>();

  public Validator(T t) {
    this.t = t;
  }

  public static <T> Validator<T> of(T t) {
    return new Validator<>(t);
  }

  public Validator<T> validate(Predicate<? super T> predicate, final String message) {
    if (!predicate.test(t)) {
      exceptions.add(new IllegalStateException(message));
    }

    return this;
  }

  public <U> Validator<T> validate(Function<? super T, ? extends U> projection,
      Predicate<? super U> predicate, final String message) {

    return validate(projection.andThen(predicate::test)::apply, message);
  }

  public static void main(String[] args) {

    final Personne personne = new Personne();
    personne.setNom("Zizou");
    personne.setAge(15);

    Validator.of(personne)
        .validate(Personne::getNom, nom -> nom != null, "Name of user must not be empty")
        .validate(Personne::getAge, age -> age > 10, "Age must be greather than 10");
  }
}