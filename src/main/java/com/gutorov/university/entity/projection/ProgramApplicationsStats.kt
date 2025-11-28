package com.gutorov.university.entity.projection

interface ProgramApplicationsStats {
    val programName: String
    val totalApplications: Long
    val admittedCount: Long
    val admissionRate: Double?
}