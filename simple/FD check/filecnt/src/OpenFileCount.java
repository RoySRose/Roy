import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;



public class OpenFileCount{

    //private static final String vendorName;

    public static void main(String[] args){


        //vendorName = profilerConfig.getProfilerJvmVendorName();

        OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();

        try {
            Method getMaxFileDescriptorCountField = osMxBean.getClass().getDeclaredMethod("getMaxFileDescriptorCount");
            Method getOpenFileDescriptorCountField = osMxBean.getClass().getDeclaredMethod("getOpenFileDescriptorCount");
            getMaxFileDescriptorCountField.setAccessible(true);
            getOpenFileDescriptorCountField.setAccessible(true);
            System.out.println(osMxBean.getName());
            System.out.println(getOpenFileDescriptorCountField.invoke(osMxBean) + "/" + getMaxFileDescriptorCountField.invoke(osMxBean));
        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
        }

        //JvmType jvmType = JvmType.fromVendor(vendorName);

//        System.out.println("==================");
//        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
//        if(os instanceof UnixOperatingSystemMXBean){
//            System.out.println("Number of open fd: " + ((UnixOperatingSystemMXBean) os).getOpenFileDescriptorCount());
//            System.out.println(((UnixOperatingSystemMXBean) os).getName());
//            //System.out.println(((UnixOperatingSystemMXBean) os).getAvailableProcessors());
//
//        }
    }
}