package br.com.bluesoft.guardian.fixture;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.bluesoft.guardian.faker.Faker;

public abstract class AbstractGuardianFixture implements GuardianFixture {

    private final GuardianFixtureConfig guardianFixtureConfig;

    AbstractGuardianFixture(GuardianFixtureConfig guardianFixtureConfig) {
        Objects.requireNonNull(guardianFixtureConfig);
        this.guardianFixtureConfig = guardianFixtureConfig;
    }

    @Override
    public Faker faker() {
        return this.guardianFixtureConfig.getFaker();
    }

    @Override
    public <T> T build(Fixture<T> fixture) {
        return fixture.convert();
    }

    @Override
    public <T> List<T> build(int numberOfObjects, Fixture<T> fixture) {
        return IntStream.range(0, numberOfObjects).mapToObj(value -> build(fixture)).collect(Collectors.toList());
    }

    @Override
    public <T> T create(Fixture<T> fixture) {
        return build(fixture);
    }

    @Override
    public <T> List<T> create(int numberOfItems, Fixture<T> fixture) {
        return IntStream.range(0, numberOfItems).mapToObj(value -> create(fixture)).collect(Collectors.toList());
    }
}
