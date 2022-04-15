package com.example.fest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name  = "job_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "JobApplicationDomain.findById", query = "SELECT j FROM JobApplicationDomain j WHERE j.id = :id"),
        @NamedQuery(name = "JobApplicationDomain.getAll", query = "SELECT j FROM JobApplicationDomain j WHERE j.volunteer IS NULL"),
        @NamedQuery(name = "JobApplicationDomain.getApps", query = "SELECT j FROM JobApplicationDomain j WHERE j.volunteer IS NOT NULL")
})
public class JobApplicationDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column()
    private String title;
    @Column
    private String text;
    @JoinColumn(name = "volunteer_id")
    @ManyToOne
    private UserDomain volunteer;
    @JoinColumn(name = "employer_id")
    @ManyToOne
    private UserDomain employer;

    public JobApplicationDomain(String title, String text){
        this.title = title;
        this.text = text;
    }

}
