package eg.gov.iti.jets.service.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DescribeSubnetsCommand {
    private List<String> subnetIds;
}

