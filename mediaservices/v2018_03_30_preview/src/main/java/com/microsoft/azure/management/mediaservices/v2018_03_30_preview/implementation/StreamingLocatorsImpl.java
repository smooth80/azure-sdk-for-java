/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 *
 */

package com.microsoft.azure.management.mediaservices.v2018_03_30_preview.implementation;

import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.mediaservices.v2018_03_30_preview.StreamingLocators;
import rx.Completable;
import rx.Observable;
import rx.functions.Func1;
import com.microsoft.azure.Page;
import com.microsoft.azure.management.mediaservices.v2018_03_30_preview.ListContentKeysResponse;
import com.microsoft.azure.management.mediaservices.v2018_03_30_preview.ListPathsResponse;
import com.microsoft.azure.management.mediaservices.v2018_03_30_preview.StreamingLocator;

class StreamingLocatorsImpl extends WrapperImpl<StreamingLocatorsInner> implements StreamingLocators {
    private final MediaManager manager;

    StreamingLocatorsImpl(MediaManager manager) {
        super(manager.inner().streamingLocators());
        this.manager = manager;
    }

    public MediaManager manager() {
        return this.manager;
    }

    @Override
    public StreamingLocatorImpl define(String name) {
        return new StreamingLocatorImpl(name, this.manager());
    }

    private StreamingLocatorImpl wrapModel(StreamingLocatorInner inner) {
        return  new StreamingLocatorImpl(inner, manager());
    }

    @Override
    public Observable<ListContentKeysResponse> listContentKeysAsync(String resourceGroupName, String accountName, String streamingLocatorName) {
        StreamingLocatorsInner client = this.inner();
        return client.listContentKeysAsync(resourceGroupName, accountName, streamingLocatorName)
        .map(new Func1<ListContentKeysResponseInner, ListContentKeysResponse>() {
            @Override
            public ListContentKeysResponse call(ListContentKeysResponseInner inner) {
                return new ListContentKeysResponseImpl(inner, manager());
            }
        });
    }

    @Override
    public Observable<ListPathsResponse> listPathsAsync(String resourceGroupName, String accountName, String streamingLocatorName) {
        StreamingLocatorsInner client = this.inner();
        return client.listPathsAsync(resourceGroupName, accountName, streamingLocatorName)
        .map(new Func1<ListPathsResponseInner, ListPathsResponse>() {
            @Override
            public ListPathsResponse call(ListPathsResponseInner inner) {
                return new ListPathsResponseImpl(inner, manager());
            }
        });
    }

    private Observable<Page<StreamingLocatorInner>> listByMediaserviceNextInnerPageAsync(String nextLink) {
        if (nextLink == null) {
            Observable.empty();
        }
        StreamingLocatorsInner client = this.inner();
        return client.listNextAsync(nextLink)
        .flatMap(new Func1<Page<StreamingLocatorInner>, Observable<Page<StreamingLocatorInner>>>() {
            @Override
            public Observable<Page<StreamingLocatorInner>> call(Page<StreamingLocatorInner> page) {
                return Observable.just(page).concatWith(listByMediaserviceNextInnerPageAsync(page.nextPageLink()));
            }
        });
    }
    @Override
    public Observable<StreamingLocator> listByMediaserviceAsync(final String resourceGroupName, final String accountName) {
        StreamingLocatorsInner client = this.inner();
        return client.listAsync(resourceGroupName, accountName)
        .flatMap(new Func1<Page<StreamingLocatorInner>, Observable<Page<StreamingLocatorInner>>>() {
            @Override
            public Observable<Page<StreamingLocatorInner>> call(Page<StreamingLocatorInner> page) {
                return listByMediaserviceNextInnerPageAsync(page.nextPageLink());
            }
        })
        .flatMapIterable(new Func1<Page<StreamingLocatorInner>, Iterable<StreamingLocatorInner>>() {
            @Override
            public Iterable<StreamingLocatorInner> call(Page<StreamingLocatorInner> page) {
                return page.items();
            }
       })
        .map(new Func1<StreamingLocatorInner, StreamingLocator>() {
            @Override
            public StreamingLocator call(StreamingLocatorInner inner) {
                return wrapModel(inner);
            }
       });
    }

    @Override
    public Observable<StreamingLocator> getByMediaserviceAsync(String resourceGroupName, String accountName, String streamingLocatorName) {
        StreamingLocatorsInner client = this.inner();
        return client.getAsync(resourceGroupName, accountName, streamingLocatorName)
        .map(new Func1<StreamingLocatorInner, StreamingLocator>() {
            @Override
            public StreamingLocator call(StreamingLocatorInner inner) {
                return wrapModel(inner);
            }
       });
    }

    @Override
    public Completable deleteByMediaserviceAsync(String resourceGroupName, String accountName, String streamingLocatorName) {
        StreamingLocatorsInner client = this.inner();
        return client.deleteAsync(resourceGroupName, accountName, streamingLocatorName).toCompletable();
    }

}
