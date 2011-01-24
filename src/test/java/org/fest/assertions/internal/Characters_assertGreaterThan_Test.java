/*
 * Created on Oct 24, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeGreater.shouldBeGreater;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.unexpectedNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Characters#assertGreaterThan(AssertionInfo, Character, char)}</code>.
 *
 * @author Alex Ruiz
 */
public class Characters_assertGreaterThan_Test {

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Characters characters;

  @Before public void setUp() {
    failures = spy(new Failures());
    characters = new Characters();
    characters.failures = failures;
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(unexpectedNull());
    characters.assertGreaterThan(someInfo(), null, 'a');
  }

  @Test public void should_pass_if_actual_is_greater_than_other() {
    characters.assertGreaterThan(someInfo(), 'b', 'a');
  }

  @Test public void should_fail_if_actual_is_equal_to_other() {
    AssertionInfo someInfo = someInfo();
    try {
      characters.assertGreaterThan(someInfo, 'b', 'b');
    } catch (AssertionError e) {
      verify(failures).failure(someInfo, shouldBeGreater('b', 'b'));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test public void should_fail_if_actual_is_less_than_other() {
    AssertionInfo info = someInfo();
    try {
      characters.assertGreaterThan(info, 'a', 'b');
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreater('a', 'b'));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
