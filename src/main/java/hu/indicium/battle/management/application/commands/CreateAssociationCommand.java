package hu.indicium.battle.management.application.commands;

import lombok.Data;

@Data
public class CreateAssociationCommand {
    private String slug;

    private String name;

    private String logoUrl;
}