package org.example.ind11api.service;

import org.assertj.core.api.Assertions;
import org.example.ind11api.model.Faculty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {

    FacultyService service = new FacultyService();

    @Test
    void testAdd() {
        var result = service.add(new Faculty(null, "test", "test_color"));
        assertEquals(result.getId(), 0);
        assertEquals(result.getName(), "test");
        assertEquals(result.getColor(), "test_color");
    }

    @Test
    void testGet() {
        var faculty = service.add(new Faculty(null, "test", "test_color"));
        var result = service.get(faculty.getId());

        assertEquals(result.getName(), "test");
        assertEquals(result.getColor(), "test_color");
    }

    @Test
    void testUpdate() {
        var result1 = service.update(new Faculty(99999L, "updated", "updated_color"));
        assertNull(result1);

        var addedFaculty = service.add(new Faculty(null, "test", "test_color"));
        addedFaculty.setName("updated_name");
        addedFaculty.setColor("updated_color");

        var result2 = service.update(addedFaculty);
        assertEquals(result2.getName(), "updated_name");
        assertEquals(result2.getColor(), "updated_color");
    }

    @Test
    void testFilterByAge() {
        var added1 = service.add(new Faculty(null, "test1", "white"));
        var added2 = service.add(new Faculty(null, "test2", "green"));
        var added3 = service.add(new Faculty(null, "test3", "GreEn"));

        var result = service.filterByColor("GREEN");
        Assertions.assertThat(result).containsExactly(added2, added3);
    }

    @Test
    void testRemove() {
        var faculty = service.add(new Faculty(null, "test1", "white"));
        assertNotNull(service.get(faculty.getId()));
        assertTrue(service.remove(faculty.getId()));
        assertFalse(service.remove(99999999L));

    }
}