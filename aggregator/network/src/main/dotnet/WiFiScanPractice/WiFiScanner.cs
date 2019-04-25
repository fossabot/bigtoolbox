using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.Devices.Enumeration;
using Windows.Devices.WiFi;

namespace WiFiScanPractice
{
    public class WiFiScanner
    {
        public List<WiFiAdapter> Adapters;

        public WiFiScanner()
        {
            this.Adapters = new List<WiFiAdapter>();
            InitializeAllAdapters().Wait();
        }

        public async Task InitializeScanner()
        {
            await InitializeFirstAdapter();
        }

        public async Task ScanForNetworksAll()
        {
            if (this.Adapters.Count >= 1)
            {
                foreach (WiFiAdapter wiFiAdapter in Adapters)
                {
                    var startTime = DateTime.Now;
                    await wiFiAdapter.ScanAsync();
                    var endTime = DateTime.Now;
                    var duration = endTime - startTime;
                    var time = duration.ToString();
                }
            }
        }

        public async Task ScanForNetworks()
        {
            if (this.Adapters[0] != null)
            {
                var startTime = DateTime.Now;
                await this.Adapters[0].ScanAsync();
                var endTime = DateTime.Now;
                var duration = endTime - startTime;
                var time = duration.ToString();
            }
        }

        private async Task InitializeAllAdapters()
        {
            var access = await WiFiAdapter.RequestAccessAsync();

            if (access != WiFiAccessStatus.Allowed)
            {
                throw new Exception("WiFiAccessStatus not allowed");
            }
            else
            {
                var wifiAdapterResults = await DeviceInformation.FindAllAsync(WiFiAdapter.GetDeviceSelector());

                if (wifiAdapterResults.Count >= 1)
                {
                    foreach (DeviceInformation adapter in wifiAdapterResults)
                    {
                        WiFiAdapter possibleWiFiAdapter = await WiFiAdapter.FromIdAsync(adapter.Id);
                        if (possibleWiFiAdapter != null)
                        {
                            this.Adapters.Add(possibleWiFiAdapter);
                        }
                    }
                }
                else
                {
                    throw new Exception("WiFi Adapter not found.");
                }
            }
        }

        private async Task InitializeFirstAdapter()
        {
            var access = await WiFiAdapter.RequestAccessAsync();

            if (access != WiFiAccessStatus.Allowed)
            {
                throw new Exception("WiFiAccessStatus not allowed");
            }
            else
            {
                var wifiAdapterResults = await DeviceInformation.FindAllAsync(WiFiAdapter.GetDeviceSelector());

                if (wifiAdapterResults.Count >= 1)
                {
                    this.Adapters[0] = await WiFiAdapter.FromIdAsync(wifiAdapterResults[0].Id);
                }
                else
                {
                    throw new Exception("WiFi Adapter not found.");
                }
            }
        }

        

    }
}
