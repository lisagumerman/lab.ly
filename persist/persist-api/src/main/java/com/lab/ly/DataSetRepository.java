package com.lab.ly;

import com.lab.ly.model.*;
import com.lab.ly.model.io.UploadedFile;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by haswell on 1/17/15.
 */
@Repository
public interface DataSetRepository {

    DataSetDescriptor save(Account account, DataSet dataSet, String name);

    Set<DataSetDescriptor> list(Account account);

    <T extends Serializable> Column<T> getColumn(Account account, String name);


}
