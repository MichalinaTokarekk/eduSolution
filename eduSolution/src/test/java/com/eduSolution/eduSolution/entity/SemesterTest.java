package com.eduSolution.eduSolution.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SemesterTest {
    @Test
    void shouldCreateSemester() {
        String semesterName = "TestSemester";
        Semester semester = new Semester(semesterName);

        assertEquals(semesterName, semester.getName());
        assertNull(semester.getStartDate());
        assertNull(semester.getEndDate());
        assertNull(semester.getCreatedBy());
    }

    @Test
    void shouldUpdateSemester() {
        Semester semester = new Semester("TestSemester");

        semester.setName("UpdatedSemester");
        semester.setEndDate("2024-12-31");

        assertEquals("UpdatedSemester", semester.getName());
        assertEquals("2024-12-31", semester.getEndDate());
    }

    @Test
    void shouldNotBeNullAfterCreation() {
        // given
        String semesterName = "TestSemester";
        Semester semester = new Semester(semesterName);

        // then
        assertNotNull(semester);
        assertNotNull(semester.getName());
    }


}