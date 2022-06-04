package eg.gov.iti.jets.service.model;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 *
 *
 */
public class DescribeSecurityGroupsCommand {
    private List<String> securityGroupIds;
}

