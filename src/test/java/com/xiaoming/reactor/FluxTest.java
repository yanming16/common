package com.xiaoming.reactor;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * @author liangyi
 * @Date 2020/3/5
 */
public class FluxTest {

    final Subscriber<Object> subscriber = new Subscriber<Object>() {
        @Override
        public void onSubscribe(final Subscription subscription) {
            System.out.println("===onSubscribe===");
        }

        @Override
        public void onNext(final Object o) {
            System.out.println("===onNext===");
        }

        @Override
        public void onError(final Throwable throwable) {
            System.out.println("===onError===");
        }

        @Override
        public void onComplete() {
            System.out.println("===onComplete===");
        }
    };

    @Test
    public void test1() {
        final Flux<String> flux = Flux.just("1", "2");
        flux.subscribe(subscriber);
    }


}
