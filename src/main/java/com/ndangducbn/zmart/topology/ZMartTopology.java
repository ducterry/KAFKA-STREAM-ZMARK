package com.ndangducbn.zmart.topology;

import com.ndangducbn.zmart.constants.BusinessConstants;
import com.ndangducbn.zmart.constants.SourceConstants;
import com.ndangducbn.zmart.enums.EnumsFeature;
import com.ndangducbn.zmart.model.origin.Purchase;
import com.ndangducbn.zmart.model.origin.PurchasePattern;
import com.ndangducbn.zmart.model.origin.RewardAccumulator;
import com.ndangducbn.zmart.service.SecurityDBService;
import com.ndangducbn.zmart.topology.processor.ZMartProcessor;
import com.ndangducbn.zmart.topology.sink.ZMartSink;
import com.ndangducbn.zmart.topology.source.ZmartSource;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class ZMartTopology {
    private static final String PREFIX_LOG = "[CNPS_APPENDIX_CONTROLLER]_";
    private static final Logger LOGGER = LogManager.getLogger(ZMartTopology.class);


    public static Topology build(EnumsFeature feature) {
        Topology topology = null;
        switch (feature) {
            case FEATURE_1:
                StreamsBuilder streamsBuilder = new StreamsBuilder();
                // Build : Stream topic
                KStream<String, Purchase> purchaseKStream = ZmartSource.buildSource(streamsBuilder, SourceConstants.SOURCE_NODE);


                /**
                 * ====== FEATURE -1======
                 * 1) Lấy danh sách mua hàng           :First Processor
                 * 2) Lấy danh sách loại hàng hóa      : Second Processor
                 * 3) Láy danh sách dữ liệu phần thưởng: Third Processor
                 */
                KStream<String, Purchase> purchaseProcessor = ZMartProcessor.buildFirstProcessor(purchaseKStream);
                KStream<String, PurchasePattern> purchasePatternProcessor = ZMartProcessor.buildSecondProcessor(purchaseKStream);
                KStream<String, RewardAccumulator> rewardProcessor = ZMartProcessor.buildThirdProcessor(purchaseKStream);

                /**
                 * ====== FEATURE -2======
                 * 1) Lọc ra sanh sách mua hàng tối thiểu 5$         :Filter Processor
                 * 2) Tạo Branch 2 cửa hàng : Điện tử và Coffee      :Four Processor
                 * 3) Chọn lại Key cho mỗi message                   :Five Processor
                 */
                KStream<Long, Purchase> filterProcess = ZMartProcessor.buildFilterProcessor(purchaseKStream);
                Predicate<String, Purchase> isCoffee = (key, value) -> value.getDepartment().equalsIgnoreCase(BusinessConstants.DPT_COFFEE);
                Predicate<String, Purchase> isElectronics = (key, value) -> value.getDepartment().equalsIgnoreCase(BusinessConstants.DPT_ELECTRONICS);
                KStream<String, Purchase>[] kStreamByDept = purchaseKStream.branch(isCoffee, isElectronics);
                KStream<String, Purchase> coffeeProcessor = kStreamByDept[0];
                KStream<String, Purchase> electronicsProcessor = kStreamByDept[1];
                /**`
                 * Print: Show Data
                 */
                showDataByProcess(purchaseProcessor, purchasePatternProcessor, rewardProcessor, filterProcess,
                        coffeeProcessor, electronicsProcessor);

                //Build: Sink
                ZMartSink.buildFirstSink(purchaseProcessor, SourceConstants.SINK_PURCHASE);
                ZMartSink.buildSecondSink(purchasePatternProcessor, SourceConstants.SINK_PATTERN);
                ZMartSink.buildThirdSink(rewardProcessor, SourceConstants.SINK_REWARD);
                ZMartSink.buildFilterSink(filterProcess, SourceConstants.SINK_FILTER);
                ZMartSink.buildCoffeeSink(coffeeProcessor, SourceConstants.SINK_COFFEE);
                ZMartSink.buildCoffeeSink(electronicsProcessor, SourceConstants.PROCESSOR_ELECTRONICS);

                // Sercurity
                ForeachAction<String, Purchase> purchaseForeachAction = (key, value)->
                        SecurityDBService.saveRecord(new Date(value.getPurchaseDate()), value.getEmployeeId(), value.getItemPurchased());
                purchaseKStream
                        .filter((key, purchase) -> purchase.getEmployeeId().equals("SHB0280292"))
                        .foreach(purchaseForeachAction);


                // Build: TOPOLOGY
                topology = streamsBuilder.build();
                LOGGER.info("\n 100) TOPOLOGY INFO : {}", topology.describe());
                break;
            case FEATURE_2:
            default:
                break;
        }
        return topology;
    }

    private static void showDataByProcess(KStream<String, Purchase> purchaseProcessor,
                                          KStream<String, PurchasePattern> purchasePatternProcessor,
                                          KStream<String, RewardAccumulator> rewardProcessor,
                                          KStream<Long, Purchase> filterProcess,
                                          KStream<String, Purchase> coffeeProcessor,
                                          KStream<String, Purchase> electronicsProcessor) {
        purchaseProcessor.foreach((key, value) -> {
            LOGGER.info("\n 1) Data First Processor: \n" +
                    " - Key: {}\n" +
                    " - Value: {}", key.toString(), value.toString());
        });
        purchasePatternProcessor.foreach((key, value) -> {
            LOGGER.info("\n 2) Data Second Processor: \n" +
                    " - Key: {}\n" +
                    " - Value: {}", key.toString(), value.toString());
        });

        rewardProcessor.foreach((key, value) -> {
            LOGGER.info("\n 3) Data Third Processor: \n" +
                    " - Key: {}\n" +
                    " - Value: {}", key.toString(), value.toString());
        });

        filterProcess.foreach((key, value) -> {
            LOGGER.info("\n 4) Data Filter Processor: \n" +
                    " - Key: {}\n" +
                    " - Value: {}", key.toString(), value.toString());
        });

        coffeeProcessor.foreach((key, value) -> {
            LOGGER.info("\n 5) Data Coffee Processor: \n" +
                    " - Key: {}\n" +
                    " - Value: {}", key.toString(), value.toString());
        });

        electronicsProcessor.foreach((key, value) -> {
            LOGGER.info("\n 6) Data Electronics Processor: \n" +
                    " - Key: {}\n" +
                    " - Value: {}", key.toString(), value.toString());
        });
    }
}
