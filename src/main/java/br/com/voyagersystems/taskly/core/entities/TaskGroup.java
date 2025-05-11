package br.com.voyagersystems.taskly.core.entities;

import br.com.voyagersystems.taskly.core.dtos.TasksDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskGroupId;

    private String taskGroupName;

    @OneToMany(
            mappedBy = "taskGroup",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Tasks> tasks = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
