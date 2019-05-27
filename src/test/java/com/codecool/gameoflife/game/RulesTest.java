package com.codecool.gameoflife.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RulesTest {

    private Rules rules;

    @BeforeEach
    public void init() {
        rules = new Rules();
    }

    @Test
    public void sanityCheck() {
        assertThat(rules).isNotNull();
    }

    @TestFactory
    public Stream<DynamicTest> liveBecomesDead(){
        return Stream.of(
                new RuleCase(true, 0),
                new RuleCase(true, 1),
                new RuleCase(true, 4),
                new RuleCase(true, 5),
                new RuleCase(true, 6),
                new RuleCase(true, 7),
                new RuleCase(true, 8)
        ).map(ruleCase -> DynamicTest.dynamicTest(ruleCase.toString(),
                () -> assertThat(
                        rules.nextState(ruleCase.isAlive(),
                                        ruleCase.getNumberOfNeighbours()))
                        .isFalse()));
    }

    @TestFactory
    public Stream<DynamicTest> liveStaysLive(){
        return Stream.of(
                new RuleCase(true, 2),
                new RuleCase(true, 3)
        ).map(ruleCase -> DynamicTest.dynamicTest(ruleCase.toString(),
                () -> assertThat(
                        rules.nextState(ruleCase.isAlive(),
                                        ruleCase.getNumberOfNeighbours()))
                        .isTrue()));
    }

    @Test
    public void deadBecomesLive() {
        assertThat(rules.nextState(false,3)).isTrue();
    }

    @TestFactory
    public Stream<DynamicTest> deadStaysDead(){
        return Stream.of(
                new RuleCase(false, 0),
                new RuleCase(false, 1),
                new RuleCase(false, 2),
                new RuleCase(false, 4),
                new RuleCase(false, 5),
                new RuleCase(false, 6),
                new RuleCase(false, 7),
                new RuleCase(false, 8)
        ).map(ruleCase -> DynamicTest.dynamicTest(ruleCase.toString(),
                () -> assertThat(
                        rules.nextState(ruleCase.isAlive(),
                                ruleCase.getNumberOfNeighbours()))
                        .isFalse()));
    }

    @Data
    @AllArgsConstructor
    private class RuleCase {
        private boolean alive;
        private int numberOfNeighbours;
    }


}
