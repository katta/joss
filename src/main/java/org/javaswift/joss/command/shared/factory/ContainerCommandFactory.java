package org.javaswift.joss.command.shared.factory;

import org.javaswift.joss.command.shared.container.*;
import org.javaswift.joss.command.shared.object.DeleteObjectsCommand;
import org.javaswift.joss.headers.Header;
import org.javaswift.joss.instructions.ListInstructions;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;

import java.util.Collection;
import java.util.List;

public interface ContainerCommandFactory {

    ContainerInformationCommand createContainerInformationCommand(Account account, Container container, boolean allowErrorLog);

    ContainerMetadataCommand createContainerMetadataCommand(Account account, Container container,
                                                            Collection<? extends Header> headers);

    ContainerRightsCommand createContainerRightsCommand(Account account, Container container, boolean publicContainer);
    
    ContainerRightsCommand createContainerRightsCommand(Account account, Container container, String writePermission, String readPermission);

    CreateContainerCommand createCreateContainerCommand(Account account, Container container);

    DeleteContainerCommand createDeleteContainerCommand(Account account, Container container);

    ListObjectsCommand createListObjectsCommand(Account account, Container container, ListInstructions listInstructions);

    ListDirectoryCommand createListDirectoryCommand(Account account, Container container,
                                                    ListInstructions listInstructions, Character delimiter);
    DeleteObjectsCommand createDeleteObjectsCommand(Account account, Container container, List<String> objectNames);


    StoredObjectCommandFactory getStoredObjectCommandFactory();

    public String getTempUrlPrefix();

    public Character getDelimiter();

}
